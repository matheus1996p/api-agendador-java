SELECT ITEM,
       ESTAB,
       NOTA,
       ITEMDESC,
       UNIDADE,
       CAST(SUM(SALDO) AS INTEGER) SALDO
FROM(
        SELECT DISTINCT NFITEM.ITEM,
                        NFITEM.ESTAB,
                        'NF-' || NFCAB.NOTA NOTA,
                        ITEMAGRO.DESCRICAO ITEMDESC,
                        ITEMAGRO.UNIDADE ,
                        SUM(NFITEM.QUANTIDADE) - COALESCE((SELECT SUM(NFITEMAPARTIRDE.QUANTIDADE)
                                                           FROM NFITEM NFITEM_QTD
                                                                    INNER JOIN NFITEMAPARTIRDE
                                                                               ON (NFITEMAPARTIRDE.ESTAB = NFITEM_QTD.ESTAB)
                                                                                   AND(NFITEMAPARTIRDE.SEQNOTA = NFITEM_QTD.SEQNOTA)
                                                                                   AND(NFITEMAPARTIRDE.SEQNOTAITEM = NFITEM_QTD.SEQNOTAITEM)

                                                           WHERE (NFITEMAPARTIRDE.estaborigem = NFITEM.ESTAB)
                                                             AND (NFITEMAPARTIRDE.seqnotaorigem = NFITEM.SEQNOTA)
                                                             AND (NFITEMAPARTIRDE.seqnotaitemorigem = NFITEM.SEQNOTAITEM)

                                                          ),0) SALDO
        FROM NFCAB
                 LEFT JOIN NFTRANSP
                           ON nftransp.estab = NFCAB.estab
                               AND NFTRANSP.seqnota = NFCAB.seqnota
                 INNER JOIN NFITEM
                            ON (NFITEM.ESTAB = NFCAB.ESTAB)
                                AND (NFITEM.SEQNOTA = NFCAB.SEQNOTA)
                 LEFT JOIN ITEMAGRO
                           ON (ITEMAGRO.ITEM = NFITEM.ITEM)
                 INNER JOIN CONTAMOV
                            ON (CONTAMOV.NUMEROCM = NFCAB.NUMEROCM)
                 LEFT JOIN NFCFG
                           ON (NFCFG.NOTACONF = NFCAB.NOTACONF)
        WHERE (0=0)
          AND CONTAMOV.CNPJF = :CPF
        GROUP BY NFCAB.NOTA, NFITEM.ITEM, NFITEM.SEQNOTA, NFITEM.SEQNOTAITEM, NFITEM.ESTAB, ITEMAGRO.DESCRICAO, ITEMAGRO.UNIDADE)
WHERE SALDO > 0
GROUP BY ITEM,
         ESTAB,
         NOTA,
         ITEMDESC,
         UNIDADE