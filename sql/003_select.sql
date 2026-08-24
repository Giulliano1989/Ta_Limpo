USE ta_limpo;
SELECT 
	lavagem.id,
    marca.nome,
    veiculo.modelo,
    lavagem.placa,
    lavagem.data,
    lavagem.observacao,
    lavagem.status
FROM 
	lavagem
    INNER JOIN veiculo
		ON veiculo.id = lavagem.veiculo_id
	INNER JOIN marca
		ON marca.id = veiculo.marca_id
WHERE (data = '2026-08-20')    
    ;