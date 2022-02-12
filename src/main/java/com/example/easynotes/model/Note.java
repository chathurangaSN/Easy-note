package com.example.easynotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(
        name = "notes",
        uniqueConstraints = {
                @UniqueConstraint(name = "notes_title_unique", columnNames = "title")
        })
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
@Data
public class Note implements Serializable {

    @Id
    @SequenceGenerator(
            name = "notes_sequence",
            sequenceName = "notes_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notes_sequence"
    )
    private Long id;

    @NotBlank
    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String title;

    @NotBlank
    @Column(
            name = "content",
            nullable = false,
            columnDefinition = "TEXT"

    )
    private String content;

    @Column(
            nullable = false,
            updatable = false
    )
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    public Note(){}

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
}
