SELECT  NFCFG.notaconf AS COD_CONF,
        NFCFG.notaconf || ' - ' || NFCFG.descricao AS CONF
FROM NFCFG
ORDER BY 1