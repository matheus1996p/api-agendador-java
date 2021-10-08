SELECT  PEDCFG.pedidoconf AS COD_CONF,
        PEDCFG.pedidoconf || ' - ' || PEDCFG.descricao AS CONF
FROM PEDCFG
ORDER BY 1