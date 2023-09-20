<?php require 'utils.inc.php'; ?>

<?php start_page('Title'); ?>

<form class="formulaire" action="partie-3.php" method="post">
    <label>Identifiant :
        <input name="id" type="text"/>
    </label>
    <label>Civilité (sexe) :
        <br>
        <label class="gender">Homme :
            <input name="gender" value="Homme" type="radio"/></label>
        <br>
        <label class="gender">Femme :
            <input name="gender" value="Femme" type="radio"/></label>
    </label>
    <label>E-mail :
        <input id="email" name="email" type="text"/>
    </label>
    <label>Mot de passe :
        <input name="mdp" type="password"/>
    </label>
    <label>Vérification mot de passe :
        <input name="mdp_verif" type="password"/>
    </label>
    <label>Pays :
        <select name="pays">
            <option value="France">France</option>
            <option value="Italie">Italie</option>
            <option value="Angleterre">Angleterre</option>
            <option value="Algerie">Algérie</option>
            <option value="Japon">Japon</option>
            <option value="Espagne">Espagne</option>
        </select>
    </label>
    <label>Conditions générales :
        <input name="cg" type="checkbox"/>
    </label>
    <button name="action" value="mailer" type="submit">Submit</button>
    <button name="action" value="send" type="submit">Send</button>
</form>
<?php require 'data-processing.php'; ?>

<?php end_page(); ?>
