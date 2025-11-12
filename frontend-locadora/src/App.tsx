import { Admin, Resource } from 'react-admin';
import dataProvider from './dataProvider';

import { AtorList, AtorEdit, AtorShow, AtorCreate } from './resources/Ator/index.js';
import { ClasseList, ClasseEdit, ClasseShow, ClasseCreate } from './resources/Classe/index.js';
import { DiretorList, DiretorEdit, DiretorShow, DiretorCreate } from './resources/Diretor/index.js';
import { TituloList, TituloEdit, TituloShow, TituloCreate } from './resources/Titulo/index.js';
import { ItemList, ItemEdit, ItemShow, ItemCreate } from './resources/Item/index.js';
import { SocioList, SocioEdit, SocioShow, SocioCreate } from './resources/Socio/index.js';
import { DependenteList, DependenteEdit, DependenteShow, DependenteCreate } from './resources/Dependente/index.js';

function App() {
  return (
    <div>
      <Admin dataProvider={dataProvider}>
        <Resource name="atores" list={AtorList} edit={AtorEdit} show={AtorShow} create={AtorCreate} />
        <Resource name="classes" list={ClasseList} edit={ClasseEdit} show={ClasseShow} create={ClasseCreate} />
        <Resource name="diretores" list={DiretorList} edit={DiretorEdit} show={DiretorShow} create={DiretorCreate} />
        <Resource name="titulos" list={TituloList} edit={TituloEdit} show={TituloShow} create={TituloCreate} />
        <Resource name="itens" list={ItemList} edit={ItemEdit} show={ItemShow} create={ItemCreate} />
        <Resource name="socios" list={SocioList} edit={SocioEdit} show={SocioShow} create={SocioCreate} />
        <Resource name="dependentes" list={DependenteList} edit={DependenteEdit} show={DependenteShow} create={DependenteCreate} />
      </Admin>
      
    </div>
  );
}

export default App;
