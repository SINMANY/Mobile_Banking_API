package co.istad.mbanking.api.account;

import co.istad.mbanking.api.accounttype.AccountType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface AccountMapper {

    @InsertProvider(type = AccountProvider.class, method = "buildAccountInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("a") Account account);

    @SelectProvider(type = AccountProvider.class, method = "buildSelectSql")
    @Results(id = "userResultMap", value = {
            @Result(column = "account_no", property = "accountNo"),
            @Result(column = "account_name", property = "accountName"),
            @Result(column = "profile" , property = "profile"),
            @Result(column = "pin", property = "pin" ),
            @Result(column = "password", property = "password"),
            @Result(column = "phone_number", property = "phoneNumber"),
            @Result(column = "transfer_limit", property = "transferLimit"),
            @Result(column = "account_type", property = "accountType", javaType = AccountType.class, one = @One(select = "selectAccountById"))
    })
    List<Account> select();
    @Select("SELECT EXISTS (SELECT * FROM accounts WHERE id=#{id})")
    AccountType selectAccountById(@Param("id") Integer account_type);


    @Select("SELECT EXISTS (SELECT * FROM accounts WHERE id=#{id})")
    boolean existsById(@Param("id") Integer id);
    @DeleteProvider(type = AccountProvider.class, method = "buildDeleteAccountSql")
    void deleteAccountType(@Param("id") Integer id);
}
