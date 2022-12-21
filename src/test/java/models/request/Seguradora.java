package models.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Seguradora{
    public String authorizationServerId;
    public String organizationId;
    public int position;
}