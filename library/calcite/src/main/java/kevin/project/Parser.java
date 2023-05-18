package kevin.project;

import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;

public class Parser {

//    use calcite to interpret sql

        public static void main(String[] args) {
        String sql = "select * from t1";
        SqlParser.Config config = SqlParser.configBuilder().setLex(Lex.MYSQL).build();
        SqlParser parser = SqlParser.create(sql, config);
        SqlNode sqlNode = null;
        try {
            sqlNode = parser.parseStmt();
        } catch (SqlParseException e) {
            e.printStackTrace();
        }
        System.out.println(sqlNode);
    }
}