package co.istad.mbanking.api.accounttype;

import co.istad.mbanking.api.accounttype.web.AccountTypeDto;
import co.istad.mbanking.api.accounttype.web.SelectAccountTypeByNameDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface AccountTypeMapper {

    @InsertProvider(type = AccountTypeProvider.class, method =  "buildInsertAccountTypeSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("u") AccountType user);


    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectSql")
    List<AccountType> select();

    @SelectProvider(type =  AccountTypeProvider.class, method = "buildSelectAccountTypeByIdSql")
    @Results(id = "AccountResultMap", value = {
            @Result(column="id", property = "id"),
            @Result(column="name" , property = "name")
    })
    Optional<AccountType> selectById(@Param("id") Integer id);

    @SelectProvider(type =  AccountTypeProvider.class, method = "buildSelectAccountTypeByNameSql")
    @Results(id = "AccountTypeResultMap", value = {
            @Result(column="id", property = "id"),
            @Result(column="name" , property = "name")
    })
    Optional<AccountType> selectByName(@Param("name")SelectAccountTypeByNameDto selectAccountTypeByNameDto);


    @Select("SELECT EXISTS (SELECT * FROM account_types WHERE id=#{id})")
    boolean existsById(@Param("id") Integer id);
    @DeleteProvider(type = AccountTypeProvider.class, method =  "buildDeleteAccountTypeById")
    void deleteAccountById(@Param("id") Integer id);

    @UpdateProvider(type= AccountTypeProvider.class, method = "buildUpdateAccountTypeByIdSql")
    void updateById(@Param("u") AccountType accountType);
}
