package com.memo2.user.entity;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "user")
@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType)
	private int id
	private String loginId;
	private String password;
	private String name;
	private String email;
	private ZoneDateTime createdAt;
	private ZoneDateTime updatedAt;
}
