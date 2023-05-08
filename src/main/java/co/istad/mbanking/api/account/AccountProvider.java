package co.istad.mbanking.api.account;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class AccountProvider {
    private static final String tableName = "accounts";

    public String buildAccountInsertSql(@Param("a") Account account){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("account_no", "#{a.accountNo}");
            VALUES("account_name", "#{a.accountName}");
            VALUES("profile", "#{a.profile}");
            VALUES("pin", "#{a.pin}");
            VALUES("password", "#{a.password}");
            VALUES("phone_number", "#{a.phoneNumber}");
            VALUES("transfer_limit", "#{a.transferLimit}");
            VALUES("account_type", "#{a.accountType}");
        }}.toString();
    }

    public String buildSelectSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
        }}.toString();
    }
    public  String buildDeleteAccountSql(){
        return new SQL(){{
            DELETE_FROM(tableName);
            WHERE("id=#{id}");
        }}.toString();
    }


}
