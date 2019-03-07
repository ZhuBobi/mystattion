package com.zhu.bobi.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Zhu波比 on 2018/3/20.
 */
@Entity
@Table(name = "sys_role", schema = "zhu")
public class UserRole extends BaseId implements GrantedAuthority {

  private String id;

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  private String rolename;

  private String roleurl;

  private Date createdate;

  @Override
  @Transient
  public String getAuthority() {
    return rolename;
  }

  public void setRolename(String rolename) {
    this.rolename = rolename;
  }

  @Column(name = "ROLE_NAME")
  public String getRolename() {
    return rolename;
  }

  @Column(name = "ROLE_URL")
  public String getRoleurl() {
    return roleurl;
  }

  public void setRoleurl(String roleurl) {
    this.roleurl = roleurl;
  }

  @Column(name = "CREATE_DATE")
  public Date getCreatedate() {
    return createdate;
  }

  public void setCreatedate(Date createdate) {
    this.createdate = createdate;
  }
}
