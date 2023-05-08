package co.istad.mbanking.api.user;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface UserMapper {


    @InsertProvider(type = UserProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("u") User user);

    @SelectProvider(type =  UserProvider.class, method = "buildSelectByIdSql")
    @Results(id = "userResultMap", value = {
            @Result(column="student_card_id", property = "studentCardId"),
            @Result(column="is_student" , property = "isStudent")
    })
    Optional<User> selectById(@Param("id") Integer id);

    @Select("SELECT EXISTS (SELECT * FROM users WHERE id=#{id})")
    boolean existsById(@Param("id") Integer id);

    @UpdateProvider(type = UserProvider.class, method = "buildDeletedById")
    void updateDeletedById(@Param("id") Integer id, Boolean status);

    @UpdateProvider(type = UserProvider.class, method = "buildUpdateDeletedByIdSql")
    void updateIsDeletedById(@Param("id") Integer id, @Param("status") boolean status);

    @UpdateProvider(type= UserProvider.class,method = "buildUpdateByIdSql")
    void updateById(@Param("u") User user);

    @SelectProvider(type = UserProvider.class, method="buildSelectAllSql")
    @ResultMap("userResultMap")
    List<User> select(@Param("name") String name);


    @SelectProvider(type =  UserProvider.class, method = "buildSelectUserBySCISql")
    @ResultMap("userResultMap")
    Optional<User> selectBySCI(@Param("studentCardId") String studentCardId);
}
