import { Show, SimpleShowLayout, TextField, ArrayField, SingleFieldList, ChipField } from 'react-admin';

export const DiretorShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" />
            <TextField source="nome" />
            
            <ArrayField label="Títulos" source="titulos">
                <SingleFieldList>
                    <ChipField source="nome" />
                </SingleFieldList>
            </ArrayField>
        </SimpleShowLayout>
    </Show>
);