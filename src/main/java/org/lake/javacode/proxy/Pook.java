package org.lake.javacode.proxy;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pook {

    @Id @GeneratedValue @NotNull
    private Integer id;

    @Column
    private String title;


}
