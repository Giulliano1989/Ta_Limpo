use ta_limpo;

select
	lv.id,
    ma.nome,
    ve.modelo,
    lv.placa
from 
	lavagem lv
	inner join veiculo ve
		on ve.id = lv.veiculo_id
	inner join marca ma
		on ma.id = ve.marca_id
where
	(lv.status = 'aguardando')