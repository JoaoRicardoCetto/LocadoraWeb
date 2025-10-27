import {
    Create,
    SimpleForm,
    TextInput,
    DateInput,
    required,
    ReferenceInput,
    AutocompleteInput,
    NumberInput
} from 'react-admin';

export const ItemCreate = (props) => {
    const transform = (data) => ({
        ...data,
        titulo: data.titulo || null,
    });

    return (
        <Create {...props} transform={transform}>
            <SimpleForm>
                <TextInput source="numSerie" />
                <TextInput source="tipo" validate={required()}/>
                <DateInput source="dataAquisicao" validate={required()} />
                <NumberInput source="preco" validate={required()} />
                <ReferenceInput source="titulo" reference="titulo">
                    <AutocompleteInput optionText="nome" />
                </ReferenceInput>
            </SimpleForm>
        </Create>
    );
};