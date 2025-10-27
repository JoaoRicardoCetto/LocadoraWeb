import {
    Create,
    SimpleForm,
    TextInput,
    required,
    ReferenceArrayInput,
    AutocompleteArrayInput,
} from 'react-admin';

export const DiretorCreate = (props) => {
    const transform = (data) => ({
        ...data,
        titulos: data.titulos || [],
    });

    return (
        <Create {...props} transform={transform}>
            <SimpleForm>
                <TextInput source="nome" validate={required()} />
                <ReferenceArrayInput source="titulos" reference="titulo">
                    <AutocompleteArrayInput optionText="nome" />
                </ReferenceArrayInput>
            </SimpleForm>
        </Create>
    );
};