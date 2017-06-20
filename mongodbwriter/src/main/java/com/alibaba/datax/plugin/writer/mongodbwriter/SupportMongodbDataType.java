package com.alibaba.datax.plugin.writer.mongodbwriter;

import com.alibaba.datax.common.element.Column;
import com.alibaba.datax.common.element.Record;
import com.alibaba.datax.plugin.writer.mongodbwriter.util.MongoUtil;
import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.alibaba.datax.plugin.writer.mongodbwriter.util.MongoUtil.*;

import java.sql.SQLException;

/**
 * 支持的mongodb数据类型
 */
public enum SupportMongodbDataType implements DataTypeHelp {
  DOUBLE {
    @Override
    public void parseDataXType(String fileName, Column column, Document document) throws Exception {
      putDBObject(document, fileName, column.asDouble());
    }
  }, STRING {
    @Override
    public void parseDataXType(String fileName, Column column, Document document) throws Exception {
      putDBObject(document, fileName, column.asString());
    }
  }, OBJECT {
    @Override
    public void parseDataXType(String fileName, Column column, Document document) throws Exception {
      Document value = Document.parse(column.asString());
      putDBObject(document, fileName, value);
    }
  }, ARRAY {
    @Override
    public void parseDataXType(String fileName, Column column, Document document) throws Exception {
      //TODO 需要支持
    }
  }, BINARY {
    @Override
    public void parseDataXType(String fileName, Column column, Document document) throws Exception {
      putDBObject(document, fileName, column.asBytes());
    }
  }, OBJECT_ID {
    @Override
    public void parseDataXType(String fileName, Column column, Document document) throws Exception {
      ObjectId objectId = new ObjectId(column.asString());
      putDBObject(document, fileName, objectId);
    }
  }, BOOLEAN {
    @Override
    public void parseDataXType(String fileName, Column column, Document document) throws Exception {
      putDBObject(document, fileName, column.asBoolean());
    }
  }, DATE {
    @Override
    public void parseDataXType(String fileName, Column column, Document document) throws Exception {
      putDBObject(document, fileName, column.asDate());
    }
  }, INT {
    @Override
    public void parseDataXType(String fileName, Column column, Document document) throws Exception {
      putDBObject(document, fileName, column.asBigInteger());
    }
  }, TIMESTAMP {
    @Override
    public void parseDataXType(String fileName, Column column, Document document) throws Exception {
      //TODO 暂时和date没有区别
      putDBObject(document, fileName, column.asDate());
    }
  }, LONG {
    @Override
    public void parseDataXType(String fileName, Column column, Document document) throws Exception {
      putDBObject(document, fileName, column.asLong());
    }
  }, DECIMAL {
    @Override
    public void parseDataXType(String fileName, Column column, Document document) throws Exception {
      putDBObject(document, fileName, column.asBigDecimal());
    }
  };


  /**
   * 根据hive类型获取
   *
   * @param type
   * @return
   */
  public static SupportMongodbDataType getType(String type) {
    for (SupportMongodbDataType supportMongodbDataType : values()) {
      if (StringUtils.equalsIgnoreCase(supportMongodbDataType.name(), type)) {
        return supportMongodbDataType;
      }
    }
    return null;
  }
}
