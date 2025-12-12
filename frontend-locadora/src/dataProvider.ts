import simpleRestProvider from 'ra-data-simple-rest';
import { getApiUrl } from './utils/apiConfig';

const api = `http://localhost:8080`;

const dataProvider = simpleRestProvider(api);

export default dataProvider;
