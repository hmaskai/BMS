package com.shilymily.apigatewayservice.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * Created by hmaskai
 * 6/1/20.
 */


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class DateAudit {

  @CreatedDate
  @Temporal(TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date creationDate;

  @LastModifiedDate
  @Temporal(TIMESTAMP)
  @Column(nullable = false)
  private Date lastModifiedDate;

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }
}
