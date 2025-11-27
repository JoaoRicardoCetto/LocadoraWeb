import * as React from 'react';
import {
    DateField,
    Datagrid,
    DeleteButton,
    EditButton,
    FunctionField,
    List,
    NumberField,
    TextField,
    TopToolbar, // Importado para melhor estrutura de ações
    ExportButton,
    CreateButton, // Adicionado para funcionalidade
} from 'react-admin';

// Use um ícone mais adequado para exclusão ou continue com CancelIcon
import CancelIcon from '@mui/icons-material/Cancel'; 
import { Box } from '@mui/material'; // Para lidar com responsividade
import NumSerieLookup from '../NumSerieLookup';

// Função auxiliar para renderizar o Título do Item
const renderItemTitulo = (record) => record?.item?.titulo?.nome ?? record?.item?.id ?? '—';

// Componente de Ações Customizadas para a Listagem (Exemplo)
const LocacaoListActions = () => (
    <TopToolbar>
        <CreateButton label= "Nova locação"/>
    </TopToolbar>
);


export const LocacaoList = (props) => (
    <>
        <List 
            {...props} 
            sort={{ field: 'dtLocacao', order: 'DESC' }} 
            actions={<LocacaoListActions />} // Adiciona as ações na toolbar
        >
            {/* Box para garantir o scroll horizontal em telas pequenas */}
            <Box sx={{ overflowX: 'auto' }}> 
                <Datagrid rowClick="show" size="small">
                    <DateField source="dtLocacao" label="Data locação" locales="pt-BR" />
                    <DateField source="dtDevolucaoPrevista" label="Devolução prevista" locales="pt-BR" />
                    <DateField source="dtDevolucaoEfetiva" label="Devolução efetiva" locales="pt-BR" />
                    
                    {/* CORREÇÃO 1: Formatação de Moeda */}
                    <NumberField 
                        source="valorCobrado" 
                        label="Valor cobrado" 
                        options={{ style: 'currency', currency: 'BRL' }} 
                    />
                    
                    {/* CORREÇÃO 2: Formatação de Moeda */}
                    <NumberField 
                        source="multaCobrada" 
                        label="Multa" 
                        options={{ style: 'currency', currency: 'BRL' }} 
                    />
                    
                    <FunctionField label="Item" render={renderItemTitulo} />
                    <TextField source="cliente.nome" label="Cliente" />
                    
                    {/* Botões de Ação */}
                    <EditButton label=""/>
                    {/* CORREÇÃO 3: O ícone deve ser um elemento JSX */}
                    <DeleteButton label="" icon={<CancelIcon/>}/> 
                    
                </Datagrid>
            </Box>
        </List>

        {/* Componente de Devolução Rápida */}
        <div style={{ margin: '2rem' }}>
            <NumSerieLookup />
        </div>
    </>
);