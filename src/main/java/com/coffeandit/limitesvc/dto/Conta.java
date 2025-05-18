package com.coffeandit.limitesvc.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class Conta implements Serializable {

    private static final long serialVersionUID = 2806412403585360625L;

    private Long codigoAgencia;
    private Long codigoConta;

}

