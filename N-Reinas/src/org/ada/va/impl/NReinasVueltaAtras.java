package org.ada.va.impl;
/*- Nombre y Apellidos- Jose Rodriguez Maldonado
DNI.- 54104045H
Titulacion.- Ingeniria de la salud
Curso.-2
Breve descripcion de la clase (2 o 3 lineas)
*/

public class NReinasVueltaAtras extends NReinasAbstract {
	
	int n= this.getDimension();

	boolean exito;
	
	public NReinasVueltaAtras(Integer dimension) {
		super(dimension);
	}

	
	public void vueltaAtras(int etapa) {
			 if(etapa<=n){
	            solucion[etapa]=0;
	            for(Integer i=0;i<n&&(!this.isExito());i++){
	                solucion[etapa]=i;
	                if(valido(etapa)){
	                    if(etapa<n-1){
	                        vueltaAtras(etapa+1);
	                    }
	                    else{
	                        this.setExito(true);
	                    }
	                }    
	            }
	        }

	}

	

	

	
	protected Boolean valido(int etapa) { 
		
		for(int i=0; i<etapa; i++){
			if(solucion[i]==solucion[etapa]||valAbs(solucion[i]-solucion[etapa])==valAbs(i-etapa)){
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}


	
	public void vaTodas(int etapa) { 
		
		if(etapa<n){
			solucion[etapa]=0;
			for(int i=0;i<n;i++){
				solucion[etapa]=i;
				if(valido(etapa)){
					if(etapa<n-1){
						vaTodas(etapa+1);
					}
					else{
						todas.add(solucion.clone());
					}
					
				}
			}
		}
			
		}

}
	

