PROMPT Criando tabela mapas
create	table	mapas
(
	origem      	varchar2(10),
	destino		varchar2(10),
	distancia	number,
    	primary	key	(origem, destino)
) 
organization	index	compress	1;

PROMPT Criando package pkg_entrega_mercadorias
create	or	replace	package	pkg_entrega_mercadorias	as	
	type	cursor_type	is	ref	cursor;
	procedure	prc_retorna_melhor_caminho	(
								p_origem	in	varchar2,
								p_destino	in	varchar2,
								p_autonomia	in	number,	
								p_valor_litro	in	number,
								p_retorno	out	cursor_type,
								p_mens		out	varchar2
							);
	procedure	prc_insere_linhas_mapa	(
							p_origem	in	varchar2,
							p_destino	in	varchar2,
							p_distancia	in	number,
							p_mens		out	varchar2
						);
end;
/
PROMPT Criando package body pkg_entrega_mercadorias
create	or	replace	package	body	pkg_entrega_mercadorias	as
	procedure	prc_retorna_melhor_caminho	(
								p_origem	in	varchar2,
								p_destino	in	varchar2,
								p_autonomia	in	number,	
								p_valor_litro	in	number,
								p_retorno	out	cursor_type,
								p_mens		out	varchar2
							)	is
	begin
		open	p_retorno	for	
			select	origem, destino, caminho, menor_caminho, autonomia, valor_litro, custo
			from	(
				select	distinct 
					origem, 
					destino, 
					caminho,
					sum(to_number(substr(distancia,instr (distancia, ',', 1, level  ) + 1,instr (distancia, ',', 1, level+1) -instr (distancia, ',', 1, level) -1 ))) 
					over (partition	by	caminho)	menor_caminho,
					p_autonomia	autonomia,
					p_valor_litro	valor_litro,
					trunc(((sum(to_number(substr(distancia,instr (distancia, ',', 1, level  ) + 1,instr (distancia, ',', 1, level+1) -instr (distancia, ',', 1, level) -1 ))) 
					over (partition	by	caminho)/p_autonomia) *	p_valor_litro),2)	custo
				from	(
					select	connect_by_root	origem				origem,
						destino						destino,
						sys_connect_by_path(distancia, ',')||','	distancia,
						p_origem|| sys_connect_by_path(destino, ',')	caminho        
					from  	mapas
					where  	destino		=	p_destino
					start	with	origem	=	p_origem
					connect	by	nocycle	
					prior 	destino		=	origem
					)
				connect	by	
				prior	caminho					=	caminho
				and	instr	(distancia, ',', 1, level+1)	>	0
				and	prior	dbms_random.string ('p', 10)	is	not	null
				order	by 
					menor_caminho	nulls	last
				)
			where	rownum	=	1;
	exception
		when	others	then
			p_mens	:=	'Erro geral ao buscar o menor caminho. Erro: '||sqlerrm;
	end;
	--
	procedure	prc_insere_linhas_mapa	(
							p_origem	in	varchar2,
							p_destino	in	varchar2,
							p_distancia	in	number,
							p_mens		out	varchar2
						)	is
	begin
		insert	into	mapas	(origem,destino,distancia)
		values(p_origem,p_destino,p_distancia);
		commit;
	exception
		when	dup_val_on_index	then
			p_mens	:=	'Origem: '||p_origem||' e Destino:'||p_destino||' já cadastrados na malha logística.';
		when	others	then
			p_mens	:=	'Erro geral ao inserir um trecho na malha logística. Erro: '||sqlerrm;
	end;	
end;
/
