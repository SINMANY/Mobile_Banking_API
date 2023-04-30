package co.istad.mbanking.api.accounttype;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;



public class AccountTypeProvider {
    private static final String tableName = "account_types";

    public String buildInsertAccountTypeSql(@Param("u") AccountType accountType){
        return  new SQL(){{
            // TODO
            INSERT_INTO(tableName);
            VALUES("name", "#{u.name}");
        }}.toString();
    }


    public String buildSelectSql() {
        return  new SQL(){{
            // TODO
            SELECT("*");
            FROM("account_types");
        }}.toString();
    }


    public String buildSelectAccountTypeByIdSql(@Param("id") Integer id){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildDeleteAccountTypeById(){
        return new SQL(){{
            DELETE_FROM(tableName);
            WHERE("id=#{id}");
        }}.toString();
    }
}
