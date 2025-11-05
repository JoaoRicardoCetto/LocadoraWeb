import { DateField, NumberField, Show, SimpleShowLayout, TextField, ArrayField, SingleFieldList, ChipField } from 'react-admin';

export const ClasseShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" />
            <TextField source="nome" />
            <NumberField source="valor" />
            <NumberField source="prazoDevolucao" />
            <ArrayField label="Títulos" source="titulos">
                <SingleFieldList>
                    <ChipField source="nome" />
                </SingleFieldList>
            </ArrayField>
        </SimpleShowLayout>
    </Show>
);