package co.istad.mbanking.api.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    private static final String tableName = "users";

    public String buildUpdateDeletedByIdSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("is_deleted = #{status} ");
            WHERE("id = #{id}", "is_deleted =  FALSE");
        }}.toString();

    }


    public String buildDeleteByIdSql(){
        return  new SQL(){{
            DELETE_FROM(tableName);
            WHERE("id = #{id}");

        }}.toString();

    }


    public String buildInsertSql(@Param("u") User user){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("name", "#{u.name}");
            VALUES("gender", "#{u.gender}");
            VALUES("one_signal_id", "#{u.oneSignalId}");
            VALUES("student_card_id", "#{u.studentCardId}");
            VALUES("is_student", "#{u.isStudent}");
            VALUES("is_deleted", "FALSE");
        }}.toString();
    }
    public String buildSelectByIdSql(@Param("id") Integer id){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildSelectAllSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("is_deleted=FALSE");
            ORDER_BY("id DESC");
        }}.toString();
    }
}
