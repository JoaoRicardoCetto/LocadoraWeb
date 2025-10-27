import {
    Edit,
    SimpleForm,
    TextInput,
    DateInput,
    required,
    ReferenceInput,
    AutocompleteInput,
    NumberInput
} from 'react-admin';

export const ItemEdit = () => {
    const transform = (data) => ({
        ...data,
        titulo: data.titulo || null,
    });

    return (
        <Edit transform={transform}>
            <SimpleForm>
                <TextInput source="numSerie" />
                <TextInput source="tipo" validate={required()} />
                <DateInput source="dataAquisicao" validate={required()} />
                <NumberInput source="preco" validate={required()} />
                <ReferenceInput source="titulo" reference="titulo">
                    <AutocompleteInput optionText="nome" />
                </ReferenceInput>
            </SimpleForm>
        </Edit>
    );
};