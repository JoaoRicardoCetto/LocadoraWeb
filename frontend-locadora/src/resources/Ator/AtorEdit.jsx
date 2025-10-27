import {
    Edit,
    SimpleForm,
    TextInput,
    required,
    ReferenceArrayInput,
    AutocompleteArrayInput,
} from 'react-admin';

const validateRequired = required();

export const AtorEdit = (props) => {
    const transform = (formData) => {
        const payload = { ...formData };

        // Lógica de Títulos
        if (payload.titulos) {
            payload.titulosIds = payload.titulos.map(t => 
                typeof t === 'object' ? t.id : t
            );
            delete payload.titulos;
        }

        // Remove props undefined
        Object.keys(payload).forEach((k) => {
            if (payload[k] === undefined) 
                delete payload[k];
        });

        return payload;
    };

    const formatTitulos = (value) => {
        if (Array.isArray(value)) {
            return value.map(item => 
                typeof item === 'object' && item !== null ? String(item.id) : String(item)
            );
        }
        return [];
    };

    return (
        <Edit {...props} transform={transform}>
            <SimpleForm>
                <TextInput source="nome" validate={validateRequired} />

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