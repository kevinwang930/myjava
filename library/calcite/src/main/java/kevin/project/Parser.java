package kevin.project;

import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.SqlDialect;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;

public class Parser {

//    use calcite to interpret sql

        public static void main(String[] args) {
        String sql = "select * from t1 where name = '中国人'";
        SqlParser.Config config = SqlParser.config().withLex(Lex.MYSQL);
        SqlParser parser = SqlParser.create(sql, config);
        SqlNode sqlNode = null;
        SqlDialect dialect = new CustomUnicodeSqlDialect(SqlDialect.DatabaseProduct.UNKNOWN, "CustomUnicode", "\"");
        try {
            sqlNode = parser.parseStmt();
            System.out.println(sqlNode.toSqlString(dialect));
        } catch (SqlParseException e) {
            e.printStackTrace();
        }
    }
}