/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.cards.s;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.CitysBlessingCondition;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.decorator.ConditionalRestrictionEffect;
import mage.abilities.effects.ContinuousEffect;
import mage.abilities.effects.Effect;
import mage.abilities.effects.RestrictionEffect;
import mage.abilities.effects.common.combat.CantBeBlockedSourceEffect;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.keyword.AscendAbility;
import mage.abilities.keyword.HexproofAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.Zone;

/**
 *
 * @author LevelX2
 */
public class SlipperyScoundrel extends CardImpl {

    public SlipperyScoundrel(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{U}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.PIRATE);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Ascend
        this.addAbility(new AscendAbility());

        // As long as you have the city's blessing Slippery Scoundrel has Hexproof and can't be blocked.
        ContinuousEffect boostSource = new GainAbilitySourceEffect(HexproofAbility.getInstance(), Duration.WhileOnBattlefield);
        ConditionalContinuousEffect effect = new ConditionalContinuousEffect(boostSource, CitysBlessingCondition.instance,
                "As long as you have the city's blessing {this} has Hexproof");
        Ability ability = new SimpleStaticAbility(Zone.BATTLEFIELD, effect);
        RestrictionEffect restrictionEffect = new CantBeBlockedSourceEffect(Duration.WhileOnBattlefield);
        Effect effect2 = new ConditionalRestrictionEffect(restrictionEffect, CitysBlessingCondition.instance)
                .setText("and can't be blocked");
        ability.addEffect(effect2);
        this.addAbility(ability);
    }

    public SlipperyScoundrel(final SlipperyScoundrel card) {
        super(card);
    }

    @Override
    public SlipperyScoundrel copy() {
        return new SlipperyScoundrel(this);
    }
}
