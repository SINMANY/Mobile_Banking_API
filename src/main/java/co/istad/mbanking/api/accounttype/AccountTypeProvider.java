package co.istad.mbanking.api.accounttype;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;



public class AccountTypeProvider {
    private static final String AccountTypesTable = "account_types";

    public String buildInsertAccountTypeSql(@Param("u") AccountType accountType){
        return  new SQL(){{
            // TODO
            INSERT_INTO(AccountTypesTable);
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
            FROM(AccountTypesTable);
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildSelectAccountTypeByNameSql(){
        return new SQL(){{
            SELECT("*");
            FROM(AccountTypesTable);
            WHERE("name=#{name.name}");
        }}.toString();
    }

    public String buildUpdateAccountTypeByIdSql() {
        return new SQL(){{
            UPDATE(AccountTypesTable);
            SET("name=#{u.name}");
            WHERE("id=#{u.id}");
        }}.toString();
    }

}
