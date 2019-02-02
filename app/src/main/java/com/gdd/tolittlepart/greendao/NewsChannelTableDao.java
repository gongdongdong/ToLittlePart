package com.gdd.tolittlepart.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.gdd.tolittlepart.entity.NewsChannelTable;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NEWS_CHANNEL_TABLE".
*/
public class NewsChannelTableDao extends AbstractDao<NewsChannelTable, String> {

    public static final String TABLENAME = "NEWS_CHANNEL_TABLE";

    /**
     * Properties of entity NewsChannelTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property NewsChannelName = new Property(0, String.class, "newsChannelName", true, "NEWS_CHANNEL_NAME");
        public final static Property NewsChannelId = new Property(1, String.class, "newsChannelId", false, "NEWS_CHANNEL_ID");
        public final static Property NewsChannelType = new Property(2, String.class, "newsChannelType", false, "NEWS_CHANNEL_TYPE");
        public final static Property NewsChannelSelect = new Property(3, boolean.class, "newsChannelSelect", false, "NEWS_CHANNEL_SELECT");
        public final static Property NewsChannelIndex = new Property(4, int.class, "newsChannelIndex", false, "NEWS_CHANNEL_INDEX");
        public final static Property NewsChannelFixed = new Property(5, Boolean.class, "newsChannelFixed", false, "NEWS_CHANNEL_FIXED");
    }


    public NewsChannelTableDao(DaoConfig config) {
        super(config);
    }
    
    public NewsChannelTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEWS_CHANNEL_TABLE\" (" + //
                "\"NEWS_CHANNEL_NAME\" TEXT PRIMARY KEY NOT NULL ," + // 0: newsChannelName
                "\"NEWS_CHANNEL_ID\" TEXT NOT NULL ," + // 1: newsChannelId
                "\"NEWS_CHANNEL_TYPE\" TEXT NOT NULL ," + // 2: newsChannelType
                "\"NEWS_CHANNEL_SELECT\" INTEGER NOT NULL ," + // 3: newsChannelSelect
                "\"NEWS_CHANNEL_INDEX\" INTEGER NOT NULL ," + // 4: newsChannelIndex
                "\"NEWS_CHANNEL_FIXED\" INTEGER);"); // 5: newsChannelFixed
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_NEWS_CHANNEL_TABLE_NEWS_CHANNEL_NAME ON NEWS_CHANNEL_TABLE" +
                " (\"NEWS_CHANNEL_NAME\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEWS_CHANNEL_TABLE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NewsChannelTable entity) {
        stmt.clearBindings();
 
        String newsChannelName = entity.getNewsChannelName();
        if (newsChannelName != null) {
            stmt.bindString(1, newsChannelName);
        }
        stmt.bindString(2, entity.getNewsChannelId());
        stmt.bindString(3, entity.getNewsChannelType());
        stmt.bindLong(4, entity.getNewsChannelSelect() ? 1L: 0L);
        stmt.bindLong(5, entity.getNewsChannelIndex());
 
        Boolean newsChannelFixed = entity.getNewsChannelFixed();
        if (newsChannelFixed != null) {
            stmt.bindLong(6, newsChannelFixed ? 1L: 0L);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NewsChannelTable entity) {
        stmt.clearBindings();
 
        String newsChannelName = entity.getNewsChannelName();
        if (newsChannelName != null) {
            stmt.bindString(1, newsChannelName);
        }
        stmt.bindString(2, entity.getNewsChannelId());
        stmt.bindString(3, entity.getNewsChannelType());
        stmt.bindLong(4, entity.getNewsChannelSelect() ? 1L: 0L);
        stmt.bindLong(5, entity.getNewsChannelIndex());
 
        Boolean newsChannelFixed = entity.getNewsChannelFixed();
        if (newsChannelFixed != null) {
            stmt.bindLong(6, newsChannelFixed ? 1L: 0L);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public NewsChannelTable readEntity(Cursor cursor, int offset) {
        NewsChannelTable entity = new NewsChannelTable( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // newsChannelName
            cursor.getString(offset + 1), // newsChannelId
            cursor.getString(offset + 2), // newsChannelType
            cursor.getShort(offset + 3) != 0, // newsChannelSelect
            cursor.getInt(offset + 4), // newsChannelIndex
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0 // newsChannelFixed
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NewsChannelTable entity, int offset) {
        entity.setNewsChannelName(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setNewsChannelId(cursor.getString(offset + 1));
        entity.setNewsChannelType(cursor.getString(offset + 2));
        entity.setNewsChannelSelect(cursor.getShort(offset + 3) != 0);
        entity.setNewsChannelIndex(cursor.getInt(offset + 4));
        entity.setNewsChannelFixed(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0);
     }
    
    @Override
    protected final String updateKeyAfterInsert(NewsChannelTable entity, long rowId) {
        return entity.getNewsChannelName();
    }
    
    @Override
    public String getKey(NewsChannelTable entity) {
        if(entity != null) {
            return entity.getNewsChannelName();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NewsChannelTable entity) {
        return entity.getNewsChannelName() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
