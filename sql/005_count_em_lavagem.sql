use ta_limpo;

SELECT
    COUNT(lv.id) total
FROM 	
	lavagem lv
WHERE
	(lv.data = '2026-08-20')
    AND (lv.status = 'lavando')
GROUP BY 
	lv.status,
    lv.data
    