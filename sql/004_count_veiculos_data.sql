use ta_limpo;

SELECT
    COUNT(lv.id) total
FROM 	
	lavagem lv
WHERE (lv.data = '2026-08-20')
GROUP BY lv.data
    