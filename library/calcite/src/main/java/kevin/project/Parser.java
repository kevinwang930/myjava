package kevin.project;

import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.SqlDialect;
import org.apache.calcite.sql.SqlInsert;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;

public class Parser {

//    use calcite to interpret sql

        public static void main(String[] args) {
        testDynamicParam();
    }


    public static void testDynamicParam() {
            String sql = "insert into test values(?, ?)";
        SqlParser.Config config = SqlParser.config().withLex(Lex.MYSQL);
        SqlParser parser = SqlParser.create(sql, config);

        SqlDialect dialect = new CustomUnicodeSqlDialect(SqlDialect.DatabaseProduct.UNKNOWN, "CustomUnicode", "\"");
        try {
            SqlInsert sqlInsert = (SqlInsert) parser.parseStmt();
            System.out.println(sqlInsert.toSqlString(dialect));
        } catch (SqlParseException e) {
            e.printStackTrace();
        }
    }
}