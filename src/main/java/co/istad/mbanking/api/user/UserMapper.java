package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.SelectByStudentCardIdDto;
import co.istad.mbanking.api.user.web.SelectUserByNameDto;
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

//  Find users by student card id
    @SelectProvider(type =  UserProvider.class, method = "buildSelectUserByStudentCardIdSql")
    @Results(id = "usersResultMapOfStudentCardId", value = {
        @Result(column="student_card_id", property = "studentCardId"),
        @Result(column="is_student" , property = "isStudent")

    })
    Optional<User> selectByStudentCardId(@Param("student_card_id") SelectByStudentCardIdDto selectByStudentCardIdDto);

//   Find users by name
@SelectProvider(type =  UserProvider.class, method = "buildSelectUserByNameSql")
@Results(id = "usersResultMapOfName", value = {
        @Result(column="student_card_id", property = "studentCardId"),
        @Result(column="is_student" , property = "isStudent")

})
Optional<User> selectByName(@Param("name")SelectUserByNameDto selectUserByNameDto);



    //    select all users (page and limit)
        @SelectProvider(type = UserProvider.class, method = "buildSelectAllSql")
        @ResultMap("userResultMap")
        List<User> select();

    @Select("SELECT EXISTS (SELECT * FROM users WHERE id=#{id})")
    boolean existsById(@Param("id") Integer id);
    @DeleteProvider(type = UserProvider.class, method = "buildDeleteByIdSql")
    void deleteById(@Param("id") Integer id);


    @UpdateProvider(type = UserProvider.class, method = "buildUpdateDeletedByIdSql")
    void updateIsDeletedById(@Param("id") Integer id, @Param("status") boolean status);

    @UpdateProvider(type=UserProvider.class,method = "buildUpdateByIdSql")
    void updateById(@Param("u") User user);
}
