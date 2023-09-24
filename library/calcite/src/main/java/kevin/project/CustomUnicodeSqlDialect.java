package kevin.project;

import org.apache.calcite.sql.SqlDialect;
import org.apache.calcite.sql.dialect.CalciteSqlDialect;
import org.checkerframework.checker.nullness.qual.Nullable;

public class CustomUnicodeSqlDialect extends CalciteSqlDialect {
    public static final SqlDialect DEFAULT = new CustomUnicodeSqlDialect(DatabaseProduct.UNKNOWN, "CustomUnicode", "\"");

    public CustomUnicodeSqlDialect(DatabaseProduct databaseProduct, String databaseName, String identifierQuoteString) {
        super(SqlDialect.EMPTY_CONTEXT);
    }

    @Override
    public void quoteStringLiteral(StringBuilder buf, @Nullable String charsetName,
                                   String val) {
        buf.append(literalQuoteString);
        buf.append(val.replace(literalEndQuoteString, literalEscapedQuote));
        buf.append(literalEndQuoteString);
    }

}
