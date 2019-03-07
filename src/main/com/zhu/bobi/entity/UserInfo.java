package com.zhu.bobi.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Zhu波比 on 2018/3/20.
 */

@Entity
@Table(name = "sys_user", schema = "zhu")
public class UserInfo extends BaseId implements UserDetails {

  private String id;

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  private String username;

  @Column(name = "USER_NAME")
  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  private String password;

  @Column(name = "PASSWORD")
  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  private String sysRoleName;

  @Column(name = "SYS_ROLE_NAME")
  public String getSysRoleName() {
    return sysRoleName;
  }

  public void setSysRoleName(String sysRoleName) {
    this.sysRoleName = sysRoleName;
  }


  private String note;

  @Column(name = "NOTE")
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }


  private Boolean isAuthentiaction;

  @Column(name = "IS_AUTHENTIACTION")
  public Boolean getAuthentiaction() {
    return isAuthentiaction;
  }

  public void setAuthentiaction(Boolean authentiaction) {
    isAuthentiaction = authentiaction;
  }


  private Date createdate;

  @Column(name = "CREATE_DATE")
  public Date getCreatedate() {
    return createdate;
  }

  public void setCreatedate(Date createdate) {
    this.createdate = createdate;
  }

  private List<UserRole> roles;

  @Override
  @Transient
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  @Transient
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  @Transient
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  @Transient
  public boolean isEnabled() {
    return true;
  }

  @Override
  @Transient
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> auths = new ArrayList<>();
    List<UserRole> roles = this.getRoles();
    for (UserRole role : roles) {
      auths.add(new SimpleGrantedAuthority(role.getRolename()));
    }
    return auths;
  }

  public void setRoles(List<UserRole> roles) {
    this.roles = roles;
  }

  @Transient
  public List<UserRole> getRoles() {
    return roles;
  }
}
