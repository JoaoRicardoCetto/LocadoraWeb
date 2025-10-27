import {
    Edit,
    SimpleForm,
    TextInput,
    required,
    NumberInput,
    DateInput,
    ReferenceArrayInput,
    AutocompleteArrayInput,
} from 'react-admin';

export const ClasseEdit = () => {
    const transform = (data) => ({
        ...data,
        titulos: data.titulos || [],
    });

    return (
        <Edit transform={transform}>
            <SimpleForm>
                <TextInput source="nome" validate={required()} />
                <NumberInput source="valor" validate={required()} />
                <DateInput source="prazoDevolucao" validate={required()} />
                <ReferenceArrayInput source="titulos" reference="titulo">
                    <AutocompleteArrayInput optionText="nome" />
                </ReferenceArrayInput>
            </SimpleForm>
        </Edit>
    );
};