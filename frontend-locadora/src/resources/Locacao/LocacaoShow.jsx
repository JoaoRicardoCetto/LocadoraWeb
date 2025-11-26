import { DateField, FunctionField, NumberField, Show, SimpleShowLayout, TextField } from 'react-admin';

const renderItemTitulo = (record) => record?.item?.titulo?.nome ?? record?.item?.id ?? '—';
const renderClienteTipo = (record) => {
    if (!record?.cliente?.tipo) return '—';
    if (record.cliente.tipo === 'SOCIO') return 'Sócio';
    if (record.cliente.tipo === 'DEPENDENTE') return 'Dependente';
    return record.cliente.tipo;
};

export const LocacaoShow = (props) => (
    <Show {...props}>
        <SimpleShowLayout>
            <DateField source="dtLocacao" label="Data locação" />
            <DateField source="dtDevolucaoPrevista" label="Devolução prevista" />
            <DateField source="dtDevolucaoEfetiva" label="Devolução efetiva" />
            <NumberField source="valorCobrado" label="Valor cobrado" />
            <NumberField source="multaCobrada" label="Multa" />
            <FunctionField label="Item" render={renderItemTitulo} />
            <TextField source="cliente.nome" label="Cliente" />
            <FunctionField label="Tipo de cliente" render={renderClienteTipo} />
        </SimpleShowLayout>
    </Show>
);

