/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app.toast.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;

/** Manages {@link Snackbar}s. */
class SnackbarManager {

  static final int MSG_TIMEOUT = 0;


  private static SnackbarManager snackbarManager;

  static SnackbarManager getInstance() {
    if (snackbarManager == null) {
      snackbarManager = new SnackbarManager();
    }
    return snackbarManager;
  }

  @NonNull private final Object lock;
  @NonNull private final Handler handler;

  @Nullable private SnackbarRecord currentSnackbar;
  @Nullable private SnackbarRecord nextSnackbar;

  private SnackbarManager() {
    lock = new Object();
    handler =
        new Handler(
            Looper.getMainLooper(),
            new Handler.Callback() {
              @Override
              public boolean handleMessage(@NonNull Message message) {
                switch (message.what) {
                  case MSG_TIMEOUT:
                    handleTimeout((SnackbarRecord) message.obj);
                    return true;
                  default:
                    return false;
                }
              }
            });
  }

  interface Callback {
    void show();

    void dismiss(int event);
  }

  public void show(long duration, Callback callback) {
    synchronized (lock) {
      if (isCurrentSnackbarLocked(callback)) {
        // Means that the callback is already in the queue. We'll just update the duration
        currentSnackbar.duration = duration;

        // If this is the Snackbar currently being shown, call re-schedule it's
        // timeout
        handler.removeCallbacksAndMessages(currentSnackbar);
        scheduleTimeoutLocked(currentSnackbar);
        return;
      } else if (isNextSnackbarLocked(callback)) {
        // We'll just update the duration
        nextSnackbar.duration = duration;
      } else {
        // Else, we need to create a new record and queue it
        nextSnackbar = new SnackbarRecord(duration, callback);
      }

      if (currentSnackbar != null
          && cancelSnackbarLocked(currentSnackbar, Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE)) {
        // If we currently have a Snackbar, try and cancel it and wait in line
        return;
      } else {
        // Clear out the current snackbar
        currentSnackbar = null;
        // Otherwise, just show it now
        showNextSnackbarLocked();
      }
    }
  }

  public void dismiss(Callback callback, int event) {
    synchronized (lock) {
      if (isCurrentSnackbarLocked(callback)) {
        cancelSnackbarLocked(currentSnackbar, event);
      } else if (isNextSnackbarLocked(callback)) {
        cancelSnackbarLocked(nextSnackbar, event);
      }
    }
  }

  /**
   * Should be called when a Snackbar is no longer displayed. This is after any exit animation has
   * finished.
   */
  public void onDismissed(Callback callback) {
    synchronized (lock) {
      if (isCurrentSnackbarLocked(callback)) {
        // If the callback is from a Snackbar currently show, remove it and show a new one
        currentSnackbar = null;
        if (nextSnackbar != null) {
          showNextSnackbarLocked();
        }
      }
    }
  }

  /**
   * Should be called when a Snackbar is being shown. This is after any entrance animation has
   * finished.
   */
  public void onShown(Callback callback) {
    synchronized (lock) {
      if (isCurrentSnackbarLocked(callback)) {
        scheduleTimeoutLocked(currentSnackbar);
      }
    }
  }

  public void pauseTimeout(Callback callback) {
    synchronized (lock) {
      if (isCurrentSnackbarLocked(callback) && !currentSnackbar.paused) {
        currentSnackbar.paused = true;
        handler.removeCallbacksAndMessages(currentSnackbar);
      }
    }
  }

  public void restoreTimeoutIfPaused(Callback callback) {
    synchronized (lock) {
      if (isCurrentSnackbarLocked(callback) && currentSnackbar.paused) {
        currentSnackbar.paused = false;
        scheduleTimeoutLocked(currentSnackbar);
      }
    }
  }

  public boolean isCurrent(Callback callback) {
    synchronized (lock) {
      return isCurrentSnackbarLocked(callback);
    }
  }

  public boolean isCurrentOrNext(Callback callback) {
    synchronized (lock) {
      return isCurrentSnackbarLocked(callback) || isNextSnackbarLocked(callback);
    }
  }

  private static class SnackbarRecord {
    @NonNull final WeakReference<Callback> callback;
    long duration;
    boolean paused;

    SnackbarRecord(long duration, Callback callback) {
      this.callback = new WeakReference<>(callback);
      this.duration = duration;
    }

    boolean isSnackbar(@Nullable Callback callback) {
      return callback != null && this.callback.get() == callback;
    }
  }

  private void showNextSnackbarLocked() {
    if (nextSnackbar != null) {
      currentSnackbar = nextSnackbar;
      nextSnackbar = null;

      final Callback callback = currentSnackbar.callback.get();
      if (callback != null) {
        callback.show();
      } else {
        // The callback doesn't exist any more, clear out the Snackbar
        currentSnackbar = null;
      }
    }
  }

  private boolean cancelSnackbarLocked(@NonNull SnackbarRecord record, int event) {
    final Callback callback = record.callback.get();
    if (callback != null) {
      // Make sure we remove any timeouts for the SnackbarRecord
      handler.removeCallbacksAndMessages(record);
      callback.dismiss(event);
      return true;
    }
    return false;
  }

  private boolean isCurrentSnackbarLocked(Callback callback) {
    return currentSnackbar != null && currentSnackbar.isSnackbar(callback);
  }

  private boolean isNextSnackbarLocked(Callback callback) {
    return nextSnackbar != null && nextSnackbar.isSnackbar(callback);
  }

  private void scheduleTimeoutLocked(@NonNull SnackbarRecord r) {
    if (r.duration == 0) {
      // If we're set to indefinite, we don't want to set a timeout
      return;
    }
    handler.removeCallbacksAndMessages(r);
    handler.sendMessageDelayed(Message.obtain(handler, MSG_TIMEOUT, r), r.duration);
  }

  void handleTimeout(@NonNull SnackbarRecord record) {
    synchronized (lock) {
      if (currentSnackbar == record || nextSnackbar == record) {
        cancelSnackbarLocked(record, Snackbar.Callback.DISMISS_EVENT_TIMEOUT);
      }
    }
  }
}
