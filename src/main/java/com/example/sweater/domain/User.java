package com.example.sweater.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    // дополнительно формирует таблицу для enam, тк это не такая сложная структура, чтобы создаать что-нибуть особенное
    // fetch - параметр, который определяет как данные значения будут подгружаться относительно основной сущности
    // когда мы загружаем user'а его roles хранятся в отдельной таблице
    // FetchType.EAGER (жадный) - при запросе пользователя будет подгружать все его роли (хорошо для небольшого объема данных)
    // FetchType.LAZY - подгрузит roles только когда user непосредственно обратиться к полю (хорошо когда хранится много записей)
   @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    // описывает, что данное поле будет храниться в отдельной таблице, для которой мы не описывали меппинг
    // позволяет нам создать табличку для roles, которая будет соединяться с текущей табличкой через user_id
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
