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

const formatTitulos = (value) => {
        if (Array.isArray(value)) {
            return value.map(item => 
                typeof item === 'object' && item !== null ? String(item.id) : String(item)
            );
        }
        return [];
    };

    return (
        <Edit transform={transform}>
            <SimpleForm>
                <TextInput source="nome" validate={required()} />
                <NumberInput source="valor" validate={required()} />
                <NumberInput source="prazoDevolucao" validate={required()} />
                <ReferenceArrayInput
                    source="titulos"
                    reference="titulos"
                    allowEmpty
                    format={formatTitulos}
                    filter={{}}
                >
                    <AutocompleteArrayInput optionText="nome" />
                </ReferenceArrayInput>
            </SimpleForm>
        </Edit>
    );
};