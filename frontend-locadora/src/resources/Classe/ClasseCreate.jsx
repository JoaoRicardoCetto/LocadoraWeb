import {
    Create,
    SimpleForm,
    TextInput,
    required,
    NumberInput,
    DateInput,
    ReferenceArrayInput,
    AutocompleteArrayInput,
} from 'react-admin';

export const ClasseCreate = (props) => {
    const transform = (data) => ({
        ...data,
        titulos: data.titulos || [],
    });

    return (
        <Create {...props} transform={transform}>
            <SimpleForm>
                <TextInput source="nome" validate={required()} />
                <NumberInput source="valor" validate={required()} />
                <DateInput source="prazoDevolucao" validate={required()} />
            </SimpleForm>
        </Create>
    );
};