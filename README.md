Trabalho II — Programação Orientada a Objetos
Projeto desenvolvido em Java para a disciplina de Programação Orientada a Objetos. O trabalho consiste em um jogo de RPG com combate por turnos, no qual o jogador escolhe um personagem e enfrenta uma sequência de monstros.
Apresentação em vídeo
Assista à apresentação do projeto no YouTube: https://youtu.be/4u6F4Dn-cBg  
Sobre o jogo
O jogador pode escolher entre três classes:
- Guerreiro: personagem com maior resistência física.
- Arqueiro: personagem com bônus de precisão nos ataques.
- Mago: personagem especializado em ataques e habilidades mágicas.
Durante a campanha, o jogador enfrenta três monstros, nesta sequência:
1. Goblin
2. Orc
3. Dragão
Durante os combates, é possível realizar ataques básicos, utilizar habilidades e consumir poções. Ao vencer uma batalha, o personagem recebe pontos de atributo, recupera recursos e recebe uma recompensa que pode ser armazenada no inventário ou equipada, quando aplicável.
Funcionalidades
- Escolha do nome e da classe do personagem.
- Combate organizado em turnos.
- Ataques básicos e ataques especiais dos monstros.
- Habilidades de ataque físico, magia, cura e buff.
- Controle de vida, mana, defesa e atributos.
- Poções de vida e de mana.
- Inventário com capacidade limitada.
- Equipamentos com slots e bônus de dano ou defesa.
- Estratégias de decisão para os monstros.
- Distribuição de pontos de atributo após as vitórias.
- Sorteio e recebimento de recompensas.
- Verificação das condições de vitória e derrota.
- Execução interativa e execução roteirizada para demonstrar cenários específicos.
Conceitos de POO utilizados
O projeto utiliza os seguintes conceitos e recursos:
- Encapsulamento: atributos mantidos como privados e acesso controlado por métodos.
- Abstração: classes abstratas para representar estruturas comuns, como Personagem, Monstro, Item e Consumivel.
- Herança: especialização de personagens, monstros e itens a partir de classes-base.
- Polimorfismo: sobrescrita de métodos para implementar comportamentos específicos de cada personagem ou monstro.
- Interfaces: contratos de comportamento, como Combatente, Habilidade, Equipavel, Usavel e EstrategiaDoMonstro.
- Composição: objetos que mantêm referências a outros objetos necessários ao seu funcionamento, como personagem e inventário.
- Strategy Pattern: separação da lógica de decisão dos monstros por meio de estratégias.
