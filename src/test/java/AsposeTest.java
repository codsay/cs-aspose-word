import com.aspose.words.SaveFormat;
import com.cs.aspose.aspose.AsposeContext;
import com.cs.aspose.aspose.AsposeFactory;
import com.cs.aspose.aspose.MessageResource;
import com.cs.aspose.aspose.data.DocumentTemplateDto;
import com.cs.aspose.aspose.data.DocumentTemplateFile;
import com.cs.aspose.aspose.data.ImageDto;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Hoang DANG.
 */
public class AsposeTest {

    @Test
    public void generateDocument() throws IOException {
        Hero hero = createHero();
        List<DocumentTemplateFile> templates = new ArrayList<>();
        templates.add(new DocumentTemplateFile("hero.docx"));
        templates.add(new DocumentTemplateFile("skill.docx", "skills"));

        byte[] data = getAsposeFactory().generateDocument(new DocumentTemplateDto<>(hero, templates, SaveFormat.PDF));

        File file = File.createTempFile("cs_aspose_word_test", Long.toString(new Date().getTime()) + ".pdf");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
        }
        System.out.println(file);
    }

    private AsposeFactory getAsposeFactory() {
        AsposeFactory factory = new AsposeFactory(
            new AsposeContext(Locale.ENGLISH)
                .setMessageResource(new MessageResource() {
                    @Override
                    public String getMessage(Locale language, String key) {
                        return language + "_" + key;
                    }

                    @Override
                    public String getMessage(Locale language, String key, Object... params) {
                        return language + "_" + key;
                    }
                })
        );

        return factory;
    }

    private Hero createHero() {
        Hero hero = new Hero();
        hero.setName("Codsay");
        hero.setAge(26);
        hero.setAuto(new SpecificPower(new Skill("Simple Kick", 10), "Auto hit"));
        hero.getSkills().add(new Skill("Hit the sky", 25));
        hero.getSkills().add(new Skill("Trust me", 40));
        hero.getSkills().add(new Skill("Infinity black", 90));
        return hero;
    }

    public static class Hero {

        private String name;
        private int age;
        private ImageDto avatar;

        private List<Skill> skills = new ArrayList<>();
        private SpecificPower active;
        private SpecificPower auto;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public ImageDto getAvatar() {
            return avatar;
        }

        public void setAvatar(ImageDto avatar) {
            this.avatar = avatar;
        }

        public List<Skill> getSkills() {
            return skills;
        }

        public void setSkills(List<Skill> skills) {
            this.skills = skills;
        }

        public SpecificPower getActive() {
            return active;
        }

        public void setActive(SpecificPower active) {
            this.active = active;
        }

        public SpecificPower getAuto() {
            return auto;
        }

        public void setAuto(SpecificPower auto) {
            this.auto = auto;
        }
    }

    public static class Skill {

        private String name;
        private int power;

        public Skill(String name, int power) {
            this.name = name;
            this.power = power;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }
    }

    private class SpecificPower {

        private Skill skill;
        private String description;

        public SpecificPower(Skill skill, String description) {
            this.skill = skill;
            this.description = description;
        }

        public Skill getSkill() {
            return skill;
        }

        public void setSkill(Skill skill) {
            this.skill = skill;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
