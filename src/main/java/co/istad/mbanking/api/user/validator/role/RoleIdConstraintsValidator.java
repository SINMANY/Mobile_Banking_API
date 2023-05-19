package co.istad.mbanking.api.user.validator.role;

import co.istad.mbanking.api.user.UserMapper;
import co.istad.mbanking.api.user.validator.role.RoleIdConstraints;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class RoleIdConstraintsValidator implements ConstraintValidator<RoleIdConstraints, List<Integer>> {
    private  final UserMapper userMapper;
    @Override
    public boolean isValid(List<Integer> roleIds, ConstraintValidatorContext context) {
        for(Integer roleId : roleIds) {
            if(!userMapper.checkRolId(roleId)){
                return false;
            }
        }
        return true;
    }
}
