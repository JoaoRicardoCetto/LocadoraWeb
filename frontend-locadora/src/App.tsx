import React from 'react';
import { Admin, Resource } from 'react-admin';
import dataProvider from './dataProvider';

import { AtorList, AtorEdit, AtorShow, AtorCreate } from './resources/Ator/index.js';
import { ClasseList, ClasseEdit, ClasseShow, ClasseCreate } from './resources/Classe/index.js';
import { DiretorList, DiretorEdit, DiretorShow, DiretorCreate } from './resources/Diretor/index.js';
import { TituloList, TituloEdit, TituloShow, TituloCreate } from './resources/Titulo/index.js';
import { ItemList, ItemEdit, ItemShow, ItemCreate } from './resources/Item/index.js';
import { SocioList, SocioEdit, SocioShow, SocioCreate } from './resources/Socio/index.js';
import { DependenteList, DependenteEdit, DependenteShow, DependenteCreate } from './resources/Dependente/index.js';
import { LocacaoList, LocacaoEdit, LocacaoShow, LocacaoCreate } from './resources/Locacao/index.js';
import { PesquisaTitulo } from './resources/PesquisaTitulo/index.js';

// ícones do Material UI (escolha os que preferir)
import PersonIcon from '@mui/icons-material/Person';
import CategoryIcon from '@mui/icons-material/Category';
import SupervisorAccountIcon from '@mui/icons-material/SupervisorAccount';
import MenuBookIcon from '@mui/icons-material/MenuBook';
import Inventory2Icon from '@mui/icons-material/Inventory2';
import PeopleAltIcon from '@mui/icons-material/PeopleAlt';
import ChildCareIcon from '@mui/icons-material/ChildCare';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import SearchIcon from '@mui/icons-material/Search';

function App() {
  return (
    <div>
      <Admin dataProvider={dataProvider}>
        <Resource
          name="atores"
          list={AtorList}
          edit={AtorEdit}
          show={AtorShow}
          create={AtorCreate}
          icon={PersonIcon}
        />
        <Resource
          name="classes"
          list={ClasseList}
          edit={ClasseEdit}
          show={ClasseShow}
          create={ClasseCreate}
          icon={CategoryIcon}
        />
        <Resource
          name="diretores"
          list={DiretorList}
          edit={DiretorEdit}
          show={DiretorShow}
          create={DiretorCreate}
          icon={SupervisorAccountIcon}
        />
        <Resource
          name="titulos"
          options={{label: "Títulos"}}
          list={TituloList}
          edit={TituloEdit}
          show={TituloShow}
          create={TituloCreate}
          icon={MenuBookIcon}
        />
        <Resource
          name="itens"
          list={ItemList}
          edit={ItemEdit}
          show={ItemShow}
          create={ItemCreate}
          icon={Inventory2Icon}
        />
        <Resource
          name="socios"
          options={{label: "Sócios"}}
          list={SocioList}
          edit={SocioEdit}
          show={SocioShow}
          create={SocioCreate}
          icon={PeopleAltIcon}
        />
        <Resource
          name="dependentes"
          list={DependenteList}
          edit={DependenteEdit}
          show={DependenteShow}
          create={DependenteCreate}
          icon={ChildCareIcon}
        />
        <Resource
          name="locacao"
          options={{label: "Locações"}}
          list={LocacaoList}
          edit={LocacaoEdit}
          show={LocacaoShow}
          create={LocacaoCreate}
          icon={ShoppingCartIcon}
        />
        <Resource
          name="pesquisa-titulos"
          options={{label: "Pesquisa de Títulos"}}
          list={PesquisaTitulo}
          icon={SearchIcon}
        />
      </Admin>
    </div>
  );
}

export default App;
