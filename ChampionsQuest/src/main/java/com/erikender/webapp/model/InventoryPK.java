package com.erikender.webapp.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryPK implements Serializable {

    private int user_id;
    private int item_id;

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        InventoryPK pk = (InventoryPK) o;
        return Objects.equals( user_id, pk.user_id) &&
                Objects.equals( item_id, pk.item_id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( user_id, item_id );
    }
}
