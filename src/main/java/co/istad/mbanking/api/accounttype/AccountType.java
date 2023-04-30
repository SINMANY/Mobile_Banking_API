package co.istad.mbanking.api.accounttype;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountType {
    private Integer id;
    private String name;
}
