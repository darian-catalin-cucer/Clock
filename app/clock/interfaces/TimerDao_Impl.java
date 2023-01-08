package com.simplemobiletools.clock.interfaces;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.simplemobiletools.clock.helpers.Converters;
import com.simplemobiletools.clock.models.Timer;
import com.simplemobiletools.clock.models.TimerState;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TimerDao_Impl implements TimerDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Timer> __insertionAdapterOfTimer;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<Timer> __deletionAdapterOfTimer;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTimer;

  public TimerDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTimer = new EntityInsertionAdapter<Timer>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `timers` (`id`,`seconds`,`state`,`vibrate`,`soundUri`,`soundTitle`,`label`,`createdAt`,`channelId`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Timer value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        stmt.bindLong(2, value.getSeconds());
        final String _tmp = __converters.timerStateToJson(value.getState());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp);
        }
        final int _tmp_1 = value.getVibrate() ? 1 : 0;
        stmt.bindLong(4, _tmp_1);
        if (value.getSoundUri() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSoundUri());
        }
        if (value.getSoundTitle() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getSoundTitle());
        }
        if (value.getLabel() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getLabel());
        }
        stmt.bindLong(8, value.getCreatedAt());
        if (value.getChannelId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getChannelId());
        }
      }
    };
    this.__deletionAdapterOfTimer = new EntityDeletionOrUpdateAdapter<Timer>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `timers` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Timer value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteTimer = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM timers WHERE id=?";
        return _query;
      }
    };
  }

  @Override
  public long insertOrUpdateTimer(final Timer timer) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfTimer.insertAndReturnId(timer);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTimers(final List<Timer> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTimer.handleMultiple(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTimer(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTimer.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteTimer.release(_stmt);
    }
  }

  @Override
  public List<Timer> getTimers() {
    final String _sql = "SELECT * FROM timers ORDER BY createdAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSeconds = CursorUtil.getColumnIndexOrThrow(_cursor, "seconds");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
      final int _cursorIndexOfVibrate = CursorUtil.getColumnIndexOrThrow(_cursor, "vibrate");
      final int _cursorIndexOfSoundUri = CursorUtil.getColumnIndexOrThrow(_cursor, "soundUri");
      final int _cursorIndexOfSoundTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "soundTitle");
      final int _cursorIndexOfLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "label");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfChannelId = CursorUtil.getColumnIndexOrThrow(_cursor, "channelId");
      final List<Timer> _result = new ArrayList<Timer>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Timer _item;
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final int _tmpSeconds;
        _tmpSeconds = _cursor.getInt(_cursorIndexOfSeconds);
        final TimerState _tmpState;
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfState)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfState);
        }
        _tmpState = __converters.jsonToTimerState(_tmp);
        final boolean _tmpVibrate;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfVibrate);
        _tmpVibrate = _tmp_1 != 0;
        final String _tmpSoundUri;
        if (_cursor.isNull(_cursorIndexOfSoundUri)) {
          _tmpSoundUri = null;
        } else {
          _tmpSoundUri = _cursor.getString(_cursorIndexOfSoundUri);
        }
        final String _tmpSoundTitle;
        if (_cursor.isNull(_cursorIndexOfSoundTitle)) {
          _tmpSoundTitle = null;
        } else {
          _tmpSoundTitle = _cursor.getString(_cursorIndexOfSoundTitle);
        }
        final String _tmpLabel;
        if (_cursor.isNull(_cursorIndexOfLabel)) {
          _tmpLabel = null;
        } else {
          _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
        }
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        final String _tmpChannelId;
        if (_cursor.isNull(_cursorIndexOfChannelId)) {
          _tmpChannelId = null;
        } else {
          _tmpChannelId = _cursor.getString(_cursorIndexOfChannelId);
        }
        _item = new Timer(_tmpId,_tmpSeconds,_tmpState,_tmpVibrate,_tmpSoundUri,_tmpSoundTitle,_tmpLabel,_tmpCreatedAt,_tmpChannelId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Timer getTimer(final int id) {
    final String _sql = "SELECT * FROM timers WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSeconds = CursorUtil.getColumnIndexOrThrow(_cursor, "seconds");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
      final int _cursorIndexOfVibrate = CursorUtil.getColumnIndexOrThrow(_cursor, "vibrate");
      final int _cursorIndexOfSoundUri = CursorUtil.getColumnIndexOrThrow(_cursor, "soundUri");
      final int _cursorIndexOfSoundTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "soundTitle");
      final int _cursorIndexOfLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "label");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfChannelId = CursorUtil.getColumnIndexOrThrow(_cursor, "channelId");
      final Timer _result;
      if(_cursor.moveToFirst()) {
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final int _tmpSeconds;
        _tmpSeconds = _cursor.getInt(_cursorIndexOfSeconds);
        final TimerState _tmpState;
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfState)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfState);
        }
        _tmpState = __converters.jsonToTimerState(_tmp);
        final boolean _tmpVibrate;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfVibrate);
        _tmpVibrate = _tmp_1 != 0;
        final String _tmpSoundUri;
        if (_cursor.isNull(_cursorIndexOfSoundUri)) {
          _tmpSoundUri = null;
        } else {
          _tmpSoundUri = _cursor.getString(_cursorIndexOfSoundUri);
        }
        final String _tmpSoundTitle;
        if (_cursor.isNull(_cursorIndexOfSoundTitle)) {
          _tmpSoundTitle = null;
        } else {
          _tmpSoundTitle = _cursor.getString(_cursorIndexOfSoundTitle);
        }
        final String _tmpLabel;
        if (_cursor.isNull(_cursorIndexOfLabel)) {
          _tmpLabel = null;
        } else {
          _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
        }
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        final String _tmpChannelId;
        if (_cursor.isNull(_cursorIndexOfChannelId)) {
          _tmpChannelId = null;
        } else {
          _tmpChannelId = _cursor.getString(_cursorIndexOfChannelId);
        }
        _result = new Timer(_tmpId,_tmpSeconds,_tmpState,_tmpVibrate,_tmpSoundUri,_tmpSoundTitle,_tmpLabel,_tmpCreatedAt,_tmpChannelId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
